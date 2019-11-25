/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wmtrucking.repositories;

import com.wmtrucking.entities.MaAuthobject;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Admin
 */
@Repository
public interface authRepository extends JpaRepository<MaAuthobject, Long> {

   @Query(nativeQuery = true, value = "select u.* from ma_authobject u where u.email=?1 and u.password=?2")
    MaAuthobject findUser(String email, String password);

}
