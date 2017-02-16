package com.cifpay.insurance.service;

import java.io.File;

import org.springframework.web.multipart.MultipartFile;

import com.cifpay.starframework.model.ServiceResult;

public interface ImageUploadService {
	public ServiceResult<String> imageSave(MultipartFile imageFile, File fileAbsolutePath);
}
