package com.happyfamily.dao.impl;

import com.happyfamily.dao.FamilyDao;
import com.happyfamily.models.Family;

import java.util.ArrayList;
import java.util.List;

public class CollectionFamilyDao implements FamilyDao {
    private final List<Family> families = new ArrayList<>();

    @Override
    public List<Family> getAllFamilies() {
        return families;
    }

    @Override
    public Family getFamilyByIndex(int index) {
        if (index >= families.size() || index < 0) {
            return null;
        }

        return families.get(index);
    }

    @Override
    public boolean deleteFamily(int index) {
        if (index >= families.size() || index < 0) {
            return false;
        }

        families.remove(index);
        return true;
    }

    @Override
    public boolean deleteFamily(Family family) {
        return families.remove(family);
    }

    @Override
    public void saveFamily(Family family) {
        if(family==null){
            return;
        }

        if (!families.contains(family)) {
            families.add(family);
        } else {
            for (int i = 0; i < families.size(); i++) {
                if (families.get(i).equals(family)) {
                    families.set(i, family);
                }
            }
        }

    }

}
