package com.example.controllers;


import com.example.models.JoinResponse;
import com.example.models.Pie;
import com.example.models.ThinKP;
import com.example.repositories.PieRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.List;

/**
 * Created by kaspernissen on 11/02/2016.
 */
@RestController
@RequestMapping("/pies")
public class PieRestController {

        @Autowired
        private PieRepository repository;

        @RequestMapping(method = RequestMethod.GET)
        public ResponseEntity<Collection<Pie>> getAllPies(){
            return new ResponseEntity<Collection<Pie>>((Collection<Pie>) repository.findAll(), HttpStatus.OK);
        }

        @RequestMapping(method = RequestMethod.GET, value = "/{id}")
        public ResponseEntity<Pie> getPieWithId(@PathVariable Long id) {
            return new ResponseEntity<Pie>(repository.findOne(id),HttpStatus.OK);
        }

        @RequestMapping(method = RequestMethod.GET, params = {"name"})
        public ResponseEntity<List<Pie>> findPieWithName(@RequestParam(value="name") String name) {
            return new ResponseEntity<List<Pie>>(repository.findByName(name), HttpStatus.OK);
        }

        @RequestMapping(method = RequestMethod.POST)
        public ResponseEntity<?> addPie(@RequestBody Pie input) {
            return new ResponseEntity<Pie>(repository.save(input), HttpStatus.CREATED);
        }
        
        @RequestMapping(method = RequestMethod.GET, value = "/eco")
        public ResponseEntity<?> ecoCall(){
        	return new ResponseEntity<Object>(HttpStatus.BANDWIDTH_LIMIT_EXCEEDED);
        }
        

        @RequestMapping(method = RequestMethod.POST, value = "/add")
        public ResponseEntity<?> parsePie(@RequestBody Pie input) {
        	System.out.println(input.getName());
        	return new ResponseEntity<Object>(HttpStatus.OK);
        }
        
        @RequestMapping(method = RequestMethod.POST, value = "/join")
        public ResponseEntity<?> joinRequest(@RequestBody ThinKP input){
        	
        	System.out.println(input.getinstanceKP());
        	System.out.println(input.getToken());
        	
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON_UTF8); 
			HttpEntity<ThinKP> request = new HttpEntity<ThinKP>(input, headers);
			
			ResponseEntity<JoinResponse> joinResponse = restTemplate.exchange(
					"http://sofia2-pocdia.cloudapp.net/sib/services/api_ssap/v01/SSAPResource/"
					, HttpMethod.POST
					, request
					, JoinResponse.class);
			
        	JoinResponse join = joinResponse.getBody();
         	
        	System.out.println(join.getSessionKey());
			
        	return new ResponseEntity<JoinResponse>(join,HttpStatus.OK);
        }
}