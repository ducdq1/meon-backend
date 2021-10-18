package com.mrlep.meon.utils;

import com.mrlep.meon.xlibrary.core.entities.MessEntity;
import lombok.Data;

/**
 * Comment
 *
 * @author nguyenhungbk96@gmail.com
 */
@Data
public class ErrorMsgEntity extends MessEntity {
    private String messageCode;
}
