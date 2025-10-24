package persistence;

import org.json.JSONObject;

// Referenced from JSonSerializationDemo

public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}