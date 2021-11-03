package com.mrlep.meon.dto.request;

import com.mrlep.meon.dto.object.MediaItem;
import com.mrlep.meon.repositories.tables.entities.MenuOptionEntity;

import java.util.List;

public class UpdateStatusRequest {
    private String cancelMessage;
    private String reconfirmMessage;
    private Integer status;
    private Integer staffId;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCancelMessage() {
        return cancelMessage;
    }

    public void setCancelMessage(String cancelMessage) {
        this.cancelMessage = cancelMessage;
    }

    public String getReconfirmMessage() {
        return reconfirmMessage;
    }

    public void setReconfirmMessage(String reconfirmMessage) {
        this.reconfirmMessage = reconfirmMessage;
    }

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }
}
