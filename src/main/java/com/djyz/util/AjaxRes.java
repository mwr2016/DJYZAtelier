package com.djyz.util;

import com.djyz.domain.Customer;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter@Getter@ToString
public class AjaxRes {
    private boolean success;
    private String msg;
    private Customer customer;

}
