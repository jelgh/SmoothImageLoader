package com.jelgh.smoothimageloader.library;

import android.graphics.Bitmap;

public interface IBitmapStorageApi {

	/**
	 * Call to this method will be on a worker thread.
	 * @param uri
	 * @return
	 */
	public Bitmap getBitmap(String uri);

}
