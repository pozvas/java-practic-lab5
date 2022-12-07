package org.example;
import com.google.gson.*;

import java.lang.reflect.Type;


public class ClockShopDeserializer implements JsonDeserializer<ClockShop> {

    @Override
    public ClockShop deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        ClockShop result = new ClockShop();
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        JsonArray jsonArray = jsonObject.get("shop").getAsJsonArray();

        for(JsonElement entry : jsonArray) {
            Clocks clock;
            if(entry.getAsJsonObject().has("seconds"))
                clock = jsonDeserializationContext.deserialize(entry, ClocksSec.class);
            else
                clock = jsonDeserializationContext.deserialize(entry, Clocks.class);
            result.AddClock(clock);
        }
        return result;
    }
}
