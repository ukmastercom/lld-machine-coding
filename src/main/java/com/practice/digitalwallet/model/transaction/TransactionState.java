package com.practice.digitalwallet.model.transaction;

public enum TransactionState {
    NOT_INITIATED(0),
    AMOUNT_DEDUCTED(1),
    AMOUNT_CREDITED(2),
    SUCCESS(3),
    FAILED(4);

    int index;

    TransactionState(int index){
        this.index = index;
    }
    int getIndex(){
        return this.index;
    }

}
