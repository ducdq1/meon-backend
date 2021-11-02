package com.mrlep.meon.utils;

import com.mrlep.meon.config.ConfigValue;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

public class MediaValidateUtils {

	public static void validateFileType(MultipartFile[] files, ConfigValue configValue) throws TeleCareException {
		String fileType;
		for (MultipartFile file : files) {
			fileType = FilenameUtils.getExtension(file.getOriginalFilename());
			if (StringUtils.isNullOrEmpty(fileType) || !configValue.getMapFileSupportTypes().contains(fileType)) {
				throw new TeleCareException(ErrorApp.ERROR_INPUTPARAMS, MessagesUtils.getMessage("file.extension.invalid"), ErrorApp.ERROR_INPUTPARAMS.getCode());
			}
			if(file.getSize() > 200 *1024*1024){
				throw new TeleCareException(ErrorApp.ERROR_INPUTPARAMS, MessagesUtils.getMessage("file.size.invalid"), ErrorApp.ERROR_INPUTPARAMS.getCode());
			}

		}
	}

}
