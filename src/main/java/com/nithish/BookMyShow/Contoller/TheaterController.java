package com.nithish.BookMyShow.Contoller;

import com.nithish.BookMyShow.Requests.AddTheaterRequest;
import com.nithish.BookMyShow.Requests.AddTheatreSeatsRequest;
import com.nithish.BookMyShow.Services.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("theater")
public class TheaterController {

    @Autowired
    private TheaterService theaterService;

    @PostMapping("add")
    public ResponseEntity addTheater(@RequestBody AddTheaterRequest theaterRequest){
        String res = this.theaterService.addTheater(theaterRequest);
        return new ResponseEntity(res, HttpStatus.OK);
    }

    @PutMapping("associateSeats")
    @PreAuthorize("hasAnyRole('ADMIN', 'SUPER_ADMIN')")
    public ResponseEntity associateSeats(@RequestBody AddTheatreSeatsRequest theatreSeatsRequest){
        String res = this.theaterService.associateSeats(theatreSeatsRequest);
        return new ResponseEntity(res, HttpStatus.OK);
    }
}
