package com.esraoz.customerarchiving.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.esraoz.customerarchiving.entities.concretes.Folder;

@Repository
public interface FolderRepository extends JpaRepository<Folder, Long>{

}
