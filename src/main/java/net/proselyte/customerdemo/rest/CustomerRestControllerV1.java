package net.proselyte.customerdemo.rest;


import net.proselyte.customerdemo.model.Customer;
import net.proselyte.customerdemo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerRestControllerV1 {

    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = "{ID}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Customer> getCustomer(@PathVariable("ID") Long customerId) {
        if (customerId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Customer customer = this.customerService.getById(customerId);

        if (customer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(customer, HttpStatus.OK);
    }


    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Customer> saveCustomer(@RequestBody @Valid Customer customer) {
        HttpHeaders headers = new HttpHeaders();

        if (customer == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        this.customerService.save(customer);
        return new ResponseEntity<>(customer, headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Customer> updateCustomer(@RequestBody @Valid Customer customer, UriComponentsBuilder builder) {
        HttpHeaders headers = new HttpHeaders();
        if (customer == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        this.customerService.save(customer);
        return new ResponseEntity<>(customer, headers, HttpStatus.OK);
    }

    @RequestMapping(value = "{ID}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Customer> deleteCustomer(@PathVariable("ID") Long id) {
        Customer customer = this.customerService.getById(id);
        if (customer == null) {
            return new ResponseEntity<>(customer, HttpStatus.NOT_FOUND);
        }

        this.customerService.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

@RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Customer>> getAllCustomers(){
        List<Customer> customers = this.customerService.getAll();

        if (customers.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

}
