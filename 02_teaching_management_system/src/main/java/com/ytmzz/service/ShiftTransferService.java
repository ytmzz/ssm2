package com.ytmzz.service;

import com.ytmzz.pojo.ShiftTransfer;
import com.ytmzz.util.PageBean;

import java.util.List;

public interface ShiftTransferService {
    public boolean checkActiveRecord(Integer studentId);

    public boolean applyShiftTransfer(ShiftTransfer shiftTransfer);

    public List<ShiftTransfer> getShiftTransferList(PageBean pageBean, ShiftTransfer shiftTransfer);

    public boolean revokeApply(ShiftTransfer shiftTransfer);

    public boolean operatorHeadmaster(ShiftTransfer shiftTransfer);

    public boolean operatorSupervisor(ShiftTransfer shiftTransfer);
}
