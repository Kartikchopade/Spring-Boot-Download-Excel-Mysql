package com.example.services;

import java.io.ByteArrayInputStream;
import java.util.List;

import com.example.entity.Contact;

public interface ExcelFileService 
{
	ByteArrayInputStream export(List<Contact> contacts);
}
