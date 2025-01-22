package com.example.kkubeurakko.api.exception.order;

import com.example.kkubeurakko.global.common.BadResponseMsgEnum;
import com.example.kkubeurakko.global.exception.GlobalException;

public class OrderNotFoundException extends GlobalException {
    public OrderNotFoundException(BadResponseMsgEnum e){
        super(e);
    }
}
