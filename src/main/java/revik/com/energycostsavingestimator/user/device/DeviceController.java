package revik.com.energycostsavingestimator.user.device;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/devices")
@Tag(name = "Devices", description = "Operations for managing user devices")
@SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor
public class DeviceController {

    private final DeviceService deviceService;

    @Operation(summary = "Add device to a room", description = "Adds a device to the specified room")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Device added"),
            @ApiResponse(responseCode = "400", description = "Validation failed"),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @PostMapping
    public Device addDevice(@RequestParam Long roomId, @RequestParam Long dumbDeviceId) {
        return deviceService.addDevice(roomId, dumbDeviceId);
    }

    @Operation(summary = "Delete a device by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Deleted"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @DeleteMapping("/{id}")
    public void deleteDevice(@PathVariable Long id) {
        deviceService.deleteDevice(id);
    }

    @Operation(summary = "Get a device by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Device retrieved"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/{id}")
    public Device getDevice(@PathVariable Long id) {
        return deviceService.getDevice(id);
    }
}
