package com.enji_iot.util.Entity.bo;

import com.enji_iot.util.Entity.bean.FileInfo;

/**
 * @类:FileInfo
 * @作者:M
 */

public class FileInfoBO extends FileInfo {

	public FileInfoBO(Integer id) {

		super();
		this.setId(id);
	}

	public FileInfoBO() {
	}

	private String base64File ;

	public String getBase64File() {
		return base64File;
	}

	public void setBase64File(String base64File) {
		this.base64File = base64File;
	}
}
