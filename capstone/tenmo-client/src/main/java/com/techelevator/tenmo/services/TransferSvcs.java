package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.Transfer;
import java.util.List;


public interface TransferSvcs {


    Transfer getTransfer(int transferId);

    List<Transfer> getTransferHistory(int transferId);

    boolean updateTransfer(Transfer newTransfer);

    Transfer createTransfer(Transfer newTransfer);


}
