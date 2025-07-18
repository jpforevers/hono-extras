package org.eclipse.hono.gateway.sdk.mqtt2amqp;

import io.vertx.core.json.JsonObject;
import org.eclipse.hono.auth.Device;
import org.eclipse.hono.util.RequestResponseApiConstants;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DeviceInfo extends Device {

    private final Map<String, Object> infos = new ConcurrentHashMap<>();

    /**
     * Creates a new device for a tenant and device identifier.
     *
     * @param tenantId The tenant.
     * @param deviceId The device identifier.
     * @throws NullPointerException if any of the params is {@code null}.
     */
    public DeviceInfo(String tenantId, String deviceId) {
        super(tenantId, deviceId);
    }

    public void addInfo(String key, Object value) {
        this.infos.put(key, value);
    }

    public Object getInfo(String key) {
        return this.infos.get(key);
    }

    public JsonObject toJson() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.put(RequestResponseApiConstants.FIELD_PAYLOAD_DEVICE_ID, getDeviceId());
        jsonObject.put(RequestResponseApiConstants.FIELD_PAYLOAD_TENANT_ID, getTenantId());
        jsonObject.put("infos", infos);
        return jsonObject;
    }

}
