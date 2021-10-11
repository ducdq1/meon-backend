package com.viettel.etc.dto.object;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class ConsultingByDoctorIdDTO {
    String content;
    Date createdDate;
}
