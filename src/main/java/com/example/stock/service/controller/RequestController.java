package com.example.stock.service.controller;

import com.example.stock.dao.model.Order;
import com.example.stock.dao.model.Request;
import com.example.stock.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/requests")
public class RequestController {

    @Autowired
    private RequestService requestService;

    // Get all requests
    @GetMapping
    public ResponseEntity<List<Request>> getAllRequests() {
        List<Request> requests = requestService.getAllRequests();
        return ResponseEntity.ok(requests);
    }

    // Get requests by status (Pending, Fulfilled, Not Available)
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Request>> getRequestsByStatus(@PathVariable String status) {
        List<Request> requests = requestService.getRequestsByStatus(status);
        return ResponseEntity.ok(requests);
    }

    // Get a specific request by ID
    @GetMapping("/{requestId}")
    public ResponseEntity<Request> getRequestById(@PathVariable String requestId) {
        Request request = requestService.getRequestById(requestId);
        return request != null ? ResponseEntity.ok(request) : ResponseEntity.notFound().build();
    }

    // Create a new request
    @PostMapping
    public ResponseEntity<Request> createRequest(@RequestBody Request request) {
        Request createdRequest = requestService.createRequest(request);
        return ResponseEntity.ok(createdRequest);
    }

    // Create a list of new requests
    @PostMapping("/bulk")
    public ResponseEntity<List<Request>> createRequests(@RequestBody List<Request> requests) {
        List<Request> createdRequests = requestService.createRequests(requests);
        return ResponseEntity.ok(createdRequests);
    }


    // Update the status of a request (e.g., Fulfilled, Not Available)
    @PutMapping("/{requestId}/status")
    public ResponseEntity<Request> updateRequestStatus(@PathVariable String requestId,
                                                       @RequestParam String status,
                                                       @RequestParam String orderId) {
        Request updatedRequest = requestService.updateRequestStatus(requestId, status, orderId);
        return updatedRequest != null ? ResponseEntity.ok(updatedRequest) : ResponseEntity.notFound().build();
    }

    // Delete a request
    @DeleteMapping("/{requestId}")
    public ResponseEntity<Void> deleteRequest(@PathVariable String requestId) {
        requestService.deleteRequest(requestId);
        return ResponseEntity.noContent().build();
    }
}
