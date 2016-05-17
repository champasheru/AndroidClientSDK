package com.cs.saurabh.chatsnippet.client.parser;

import android.util.JsonReader;

/**
 * Created by saurabhATchampasheruDOTbuild on 5/13/2016.
 * Each object that needs to take part in the web service request/response API or for some reason, needs to
 * be serialized into JSON format shall implement this inferface.
 */
public interface JSONSerializable {
    //Read from the jsonReader provided & populate the fields of the domain object.
    void readJSON(JsonReader jsonReader);

    void writeJSON();
}
