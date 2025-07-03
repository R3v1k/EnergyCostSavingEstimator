package revik.com.energycostsavingestimator.user.device.dumbdevice;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dumb-devices")
@Tag(name = "Dumb Devices", description = "Admin operations for managing dumb devices")
@SecurityRequirement(name = "bearerAuth")
@PreAuthorize("hasRole('ADMIN')")
@RequiredArgsConstructor
public class DumbDeviceController {

    private final DumbDeviceService service;

    @Operation(summary = "Create a dumb device", description = "Creates a new dumb device")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Dumb device created"),
            @ApiResponse(responseCode = "400", description = "Validation failed"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden")
    })
    @PostMapping
    public DumbDevice create(@RequestBody DumbDevice dumb) {
        return service.create(dumb);
    }

    @Operation(summary = "List all dumb devices", description = "Retrieves all dumb devices (admin only)")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Dumb devices retrieved"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden")
    })
    @GetMapping
    public List<DumbDevice> list() {
        return service.findAll();
    }

    @Operation(summary = "Get a dumb device by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Dumb device retrieved"),
            @ApiResponse(responseCode = "404", description = "Not found"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden")
    })
    @GetMapping("/{id}")
    public DumbDevice get(@PathVariable Long id) {
        return service.get(id);
    }

    @Operation(summary = "Update a dumb device", description = "Updates an existing dumb device")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Dumb device updated"),
            @ApiResponse(responseCode = "404", description = "Not found"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden")
    })
    @PutMapping("/{id}")
    public DumbDevice update(@PathVariable Long id, @RequestBody DumbDevice patch) {
        return service.update(id, patch);
    }

    @Operation(summary = "Delete a dumb device by ID")
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
