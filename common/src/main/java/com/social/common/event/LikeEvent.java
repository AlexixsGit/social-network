package com.social.common.event;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.social.like.model.LikeStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LikeEvent {
    private String productId;
    private String userId;
    private String status;

    @Override
    public String toString() {
        return toJSON();
    }

    public String toJSON() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(this);
        } catch (Exception ex) {
            return "{}";
        }
    }

    public static LikeEvent fromJSON(final String json) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(json, LikeEvent.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
