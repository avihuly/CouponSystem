package com.couponproject.constants;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class CouponTypeAdapter extends XmlAdapter<String, CouponType>{
 
    public CouponType unmarshal(String v) throws Exception {
        return CouponType.valueOf(v);
    }
 
    public String marshal(CouponType v) throws Exception {
        return v.toString();
    }
}
