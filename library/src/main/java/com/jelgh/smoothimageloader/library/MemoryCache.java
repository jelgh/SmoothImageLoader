package com.jelgh.smoothimageloader.library;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class MemoryCache {

	private static final String TAG = MemoryCache.class.getSimpleName();

	private Map<String, BitmapDrawable> cache = Collections.synchronizedMap(new LinkedHashMap<String, BitmapDrawable>(10, 1.5f, true));
	private long size = 0;
	private long limit;
	private boolean debug;

	public MemoryCache(long limit, boolean debug) {
		this.limit = limit;
		this.debug = debug;
	}

	public MemoryCache(boolean debug) {
		this.limit = Runtime.getRuntime().maxMemory() / 4;
		this.debug = debug;
	}

	public MemoryCache() {
		this.limit = Runtime.getRuntime().maxMemory() / 4;
		this.debug = false;
	}

	public boolean contains(String id) {
		return cache.containsKey(id);
	}

	public BitmapDrawable get(String id) {
		try {
			if (!cache.containsKey(id)) {
				return null;
			}
			return cache.get(id);
		} catch (NullPointerException ex) {
			if (debug) ex.printStackTrace();
			return null;
		}
	}

	public void put(String id, BitmapDrawable bitmap) {
		try {
			if (cache.containsKey(id)) {
				size -= getSizeInBytes(cache.get(id));
			}
			cache.put(id, bitmap);
			size += getSizeInBytes(bitmap);
			checkSize();
		} catch (Throwable th) {
			if (debug) th.printStackTrace();
		}
	}

	private void checkSize() {
		if (debug) Log.d(TAG, "cache size=" + size + " length=" + cache.size());
		if (size > limit) {
			Iterator<Entry<String, BitmapDrawable>> iter = cache.entrySet().iterator();
			while (iter.hasNext()) {
				Entry<String, BitmapDrawable> entry = iter.next();
				size -= getSizeInBytes(entry.getValue());
				iter.remove();
				if (size <= limit) {
					break;
				}
			}
			if (debug) Log.d(TAG, "Clean cache. New size " + cache.size());
		}
	}

	public void clear() {
		try {
			cache.clear();
			size = 0;
		} catch (NullPointerException ex) {
			if (debug) ex.printStackTrace();
		}
	}

	long getSizeInBytes(BitmapDrawable drawable) {

		Bitmap bitmap;
		if (drawable == null || (bitmap = drawable.getBitmap()) == null) {
			return 0;
		}

		return bitmap.getRowBytes() * bitmap.getHeight();
	}
}