package com.example.stock.service;

import com.example.stock.dao.model.Request;
import com.example.stock.dao.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class RequestService {

    @Autowired
    private RequestRepository requestRepository;

    // Get all requests
    public List<Request> getAllRequests() {
        return requestRepository.findAll();
    }

    // Get requests by status (e.g., Pending, Fulfilled, Not Available)
    public List<Request> getRequestsByStatus(String status) {
        return requestRepository.findByStatus(status);
    }

    // Get a specific request by ID
    public Request getRequestById(String requestId) {
        Optional<Request> requestOptional = requestRepository.findById(requestId);
        return requestOptional.orElse(null);  // Return null if request not found
    }

    // Create a new request
    public Request createRequest(Request request) {
        request.setRequestDate(new Date());  // Set the current date as request date
        return requestRepository.save(request);
    }

    // Update the status of a request
    public Request updateRequestStatus(String requestId, String status, String orderId) {
        Optional<Request> requestOptional = requestRepository.findById(requestId);
        if (requestOptional.isPresent()) {
            Request request = requestOptional.get();
            request.setStatus(status);
            request.setOrderId(orderId);  // Set order ID if fulfilled
            return requestRepository.save(request);
        }
        return null;  // Request not found
    }

    // Delete a request
    public void deleteRequest(String requestId) {
        requestRepository.deleteById(requestId);
    }

    public List<Request> createRequests(List<Request> requests) {
        return requestRepository.saveAll(requests);
    }
}
