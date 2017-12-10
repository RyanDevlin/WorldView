package com.cam2.ryandevlin.worldview;
import com.google.android.gms.maps.GoogleMap;
import com.google.maps.DirectionsApi;
import com.google.maps.GeoApiContext;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.TravelMode;

import org.joda.time.DateTime;

import java.io.IOException;
import java.lang.Object;
import java.util.concurrent.TimeUnit;

/**
 * Created by maysan on 11/23/17.
 */

public class mapDirections {

    String origin;
    String destination;
    GoogleMap mMap;

    public mapDirections(String origin, String dest, GoogleMap mMap) throws InterruptedException, ApiException, IOException {
        this.origin = origin;
        this.destination = dest;
        this.mMap = mMap;
    }

    private GeoApiContext getGeoContext(){

        //connection timeout : default connection timeout for new connections
        //query rate: max number of queries that will be executed in 1 second intervals
        //the default read timeout for new connections
        //the default write timeout for new connections

        GeoApiContext geoApiContext = new GeoApiContext();
        return geoApiContext.setQueryRateLimit(3).setApiKey("AIzaSyBUk43bX4UmObgrUZooRrsS-86PxSYelbU")
                .setConnectTimeout(1, TimeUnit.SECONDS).setReadTimeout(1, TimeUnit.SECONDS)
                .setWriteTimeout(1, TimeUnit.SECONDS);
    }

    DateTime now = new DateTime();

    //mode = travelmode which can be walking, driving, etc...
    //origin is where you start
    //destination is where you want to go.
    //departure time is when you want to depart.

    DirectionsResult result = DirectionsApi.newRequest(getGeoContext())
            .mode(TravelMode.DRIVING).origin(origin)
            .destination(destination).departureTime(now)
            .await();

}