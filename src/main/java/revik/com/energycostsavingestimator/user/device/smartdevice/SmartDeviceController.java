package revik.com.energycostsavingestimator.user.device.smartdevice;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/smart-devices")
@Tag(name = "Smart Devices", description = "Admin operations for managing smart devices")
@SecurityRequirement(name = "bearerAuth")
@PreAuthorize("hasRole('ADMIN')")
@RequiredArgsConstructor
public class SmartDeviceController {

    private final SmartDeviceService service;

    @Operation(summary = "Create a smart device", description = "Creates a new smart device with optional list of supported dumb device IDs")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Smart device created"),
            @ApiResponse(responseCode = "400", description = "Validation failed"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden")
    })
    @PostMapping
    public SmartDevice create(
            @RequestParam String name,
            @RequestBody(required = false) Set<Long> supportedIds
    ) {
        return service.create(name, supportedIds == null ? Collections.emptySet() : supportedIds);
    }

    @Operation(summary = "List all smart devices", description = "Retrieves all smart devices (admin only)")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Smart devices retrieved"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden")
    })
    @GetMapping
    public List<SmartDevice> list() {
        return service.findAll();
    }

    @Operation(summary = "Get a smart device by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Smart device retrieved"),
            @ApiResponse(responseCode = "404", description = "Not found"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden")
    })
    @GetMapping("/{id}")
    public SmartDevice get(@PathVariable Long id) {
        return service.get(id);
    }

    @Operation(summary = "Rename a smart device", description = "Updates the name of a smart device")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Smart device renamed"),
            @ApiResponse(responseCode = "404", description = "Not found"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden")
    })
    @PutMapping("/{id}")
    public SmartDevice rename(
            @PathVariable Long id,
            @RequestParam String name
    ) {
        return service.updateName(id, name);
    }

    @Operation(summary = "Add supported dumb device", description = "Adds a supported dumb device to a smart device")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Supported dumb device added"),
            @ApiResponse(responseCode = "404", description = "Not found"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden")
    })
    @PostMapping("/{smartId}/supported/{dumbId}")
    public SmartDevice addSupported(
            @PathVariable Long smartId,
            @PathVariable Long dumbId
    ) {
        return service.addSupported(smartId, dumbId);
    }

    @Operation(summary = "Remove supported dumb device", description = "Removes a supported dumb device from a smart device")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Supported dumb device removed"),
            @ApiResponse(responseCode = "404", description = "Not found"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden")
    })
    @DeleteMapping("/{smartId}/supported/{dumbId}")
    public SmartDevice removeSupported(
            @PathVariable Long smartId,
            @PathVariable Long dumbId
    ) {
        return service.removeSupported(smartId, dumbId);
    }

    @Operation(summary = "Delete a smart device by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Deleted"),
            @ApiResponse(responseCode = "404", description = "Not found"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden")
    })
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
