package com.example.projection.repository.projections;


/**
 * To implement projection when there are mapped associations
 */

public interface AccountAdvancedProjection {
    String getAccountNumber();
    PersonView getOwner();

    interface PersonView {
        Long getId();
        CompanyView getCompany();
    }

    interface CompanyView {
        String getName();
    }
}
