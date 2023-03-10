package com.training.project.web.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.training.project.dto.Bank;
import com.training.project.dto.DiscountDto;
import com.training.project.services.DiscountService;

@RequestMapping("api/v1")
@RestController
public class DiscountController {

	private Logger logger = LoggerFactory.getLogger(DiscountController.class);
	@Autowired
	private DiscountService discountService;
	
	public DiscountController() {
		logger.info("Discount Controller constructor created");
	}
	
	//get all discounts
	@CrossOrigin(origins="http://localhost:4200")
	@GetMapping(value="discount")
	public ResponseEntity<List<DiscountDto>> getAllDiscounts(){
		List<DiscountDto> discountList = this.discountService.getAll();
		return new ResponseEntity<List<DiscountDto>>(discountList, HttpStatus.OK);
	}
	
	//add a new discount
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping(value="discount")
	public ResponseEntity<DiscountDto> addDiscount(@RequestBody DiscountDto discountDto){
		DiscountDto returnedDiscountDto = this.discountService.saveDiscount(discountDto);
		return new ResponseEntity<DiscountDto>(returnedDiscountDto, HttpStatus.CREATED);
	}
	
	//get discount by id
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/discount/{id}")
	public ResponseEntity<DiscountDto> getDiscount(@PathVariable Integer id){
		DiscountDto returnedDiscountDto = this.discountService.getDiscountById(id);
		System.out.println(returnedDiscountDto);
		return new ResponseEntity<DiscountDto>(returnedDiscountDto, HttpStatus.OK);
	}
	
	//get discount by bank name
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/discount/bankName/{bankName}")
	public ResponseEntity<DiscountDto> getDiscountByName(@PathVariable String bankName){
		Bank bankNameEnum = Bank.valueOf(bankName);
		DiscountDto returnedDiscountDto = this.discountService.getDiscountByBankname(bankNameEnum);
		return new ResponseEntity<DiscountDto>(returnedDiscountDto, HttpStatus.OK);
	}
	
	//update discount by discount object
	@CrossOrigin(origins = "http://localhost:4200")
	@PutMapping("/discount")
	public ResponseEntity<DiscountDto> findDiscount(@RequestBody DiscountDto discountDto){
		DiscountDto returnedDiscountDto = this.discountService.updateDiscountByBankName(discountDto);
		return new ResponseEntity<DiscountDto>(returnedDiscountDto, HttpStatus.OK);
	}

}
