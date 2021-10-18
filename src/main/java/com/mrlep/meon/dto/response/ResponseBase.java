package com.mrlep.meon.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ResponseBase {
    int code;
    String message;
    Object data;
}
