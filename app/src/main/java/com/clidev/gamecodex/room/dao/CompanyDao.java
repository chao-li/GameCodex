package com.clidev.gamecodex.room.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.clidev.gamecodex.room.entities.Company;
import com.clidev.gamecodex.room.entities.Genre;

import java.util.List;

@Dao
public interface CompanyDao {

    @Query("SELECT * FROM company ORDER BY id")
    List<Company> loadAllCompany();

    @Insert
    void insertCompany(Company company);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateCompany(Company company);

    @Delete
    void deleteCompany(Company company);

    @Query("SELECT * FROM company WHERE id = :id")
    Company loadCompanyById(Long id);

}

