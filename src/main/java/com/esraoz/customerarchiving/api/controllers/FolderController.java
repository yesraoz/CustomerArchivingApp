package com.esraoz.customerarchiving.api.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.esraoz.customerarchiving.exception.ResourceNotFoundException;
import com.esraoz.customerarchiving.dataAccess.abstracts.FolderRepository;
import com.esraoz.customerarchiving.entities.concretes.Folder;
import com.esraoz.customerarchiving.util.Constants;

@RestController
@RequestMapping("/api")
public class FolderController {

	@Autowired
	private FolderRepository folderRepository;

	@GetMapping(path = "/folders")
	public List<Folder> getAllFolders() {
		return folderRepository.findAll();
	}

	@GetMapping(path = "/folder/{id}")
	public ResponseEntity<Folder> getFolderById(@PathVariable(value = "id") Long folderId)
			throws ResourceNotFoundException {
		final Folder folder = folderRepository.findById(folderId)
				.orElseThrow(() -> new ResourceNotFoundException(Constants.ERR_MSG_FOLDER + folderId));
		return ResponseEntity.ok().body(folder);
	}

	@PostMapping(path = "/folder")
	public Folder createFolder(@RequestBody Folder folder) {
		return folderRepository.save(folder);
	}

	@PutMapping(path = "folder/{id}")
	public ResponseEntity<Folder> updateFolder(@PathVariable(value = "id") Long folderId,
			@RequestBody Folder updatedFolder) throws ResourceNotFoundException {
		final Folder folder = folderRepository.findById(folderId)
				.orElseThrow(() -> new ResourceNotFoundException(Constants.ERR_MSG_FOLDER + folderId));
		updatedFolder.setId(folder.getId());
		return ResponseEntity.ok().body(folderRepository.save(updatedFolder));

	}

	@DeleteMapping(path = "/folder/{id}")
	public Map<String, Boolean> deleteFolder(@PathVariable(value = "id") Long folderId)
			throws ResourceNotFoundException {
		final Folder folder = folderRepository.findById(folderId)
				.orElseThrow(() -> new ResourceNotFoundException(Constants.ERR_MSG_FOLDER + folderId));
		folderRepository.delete(folder);
		Map<String, Boolean> response = new HashMap<>();
		response.put(Constants.DELETED, Boolean.TRUE);
		return response;
	}

}

