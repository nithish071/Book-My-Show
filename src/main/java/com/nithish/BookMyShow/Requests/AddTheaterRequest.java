package com.nithish.BookMyShow.Requests;

import lombok.Data;

@Data
public class AddTheaterRequest {

    private String name;
    private Integer noOfScreens;
    private String address;

}
