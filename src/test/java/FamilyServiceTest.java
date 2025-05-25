import com.happyfamily.dao.FamilyDao;
import com.happyfamily.models.*;
import com.happyfamily.service.FamilyService;
import com.happyfamily.service.impl.FamilyServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FamilyServiceTest {
    private FamilyDao familyDao;
    private FamilyService familyService;

    @BeforeEach
    void setUp() {
        familyDao = mock(FamilyDao.class);
        familyService = new FamilyServiceImpl(familyDao);
    }

    @Test
    public void testCreateNewFamily() {
        Man man = new Man();
        Woman woman = new Woman();

        Family family = new Family(man, woman);

        when(familyDao.getAllFamilies()).thenReturn(Collections.emptyList());
        doNothing().when(familyDao).saveFamily(any(Family.class));

        Family newFamily = familyService.createNewFamily(man, woman);

        assertEquals(family, newFamily);
        verify(familyDao).saveFamily(any(Family.class));
    }

    @Test
    public void testDeleteByIndex() {
        when(familyDao.deleteFamily(anyInt())).thenReturn(false);

        boolean isDeleted = familyService.deleteFamilyByIndex(1);
        assertFalse(isDeleted);

        verify(familyDao).deleteFamily(1);
    }

    @Test
    public void testBornChild() {
        Man man = new Man();
        Woman woman = new Woman();

        Family family = spy(new Family(man, woman));

        doNothing().when(familyDao).saveFamily(any(Family.class));
        doNothing().when(family).addChild(any(Human.class));

        Family family1 = familyService.bornChild(family, "nahid", "sabina");

        assertEquals(family, family1);

        verify(family).addChild(any(Human.class));
        verify(familyDao).saveFamily(any(Family.class));
    }

    @Test
    public void testAdoptChild() {
        Family family = spy(new Family(new Man(), new Woman()));
        Human human = new Human();

        doNothing().when(familyDao).saveFamily(any(Family.class));
        doNothing().when(family).addChild(any(Human.class));

        familyService.adoptChild(family, human);

        assertEquals(human.getFamily(), family);

        verify(familyDao).saveFamily(any(Family.class));
    }

    @Test
    public void testGetFamilyById(){
        Man man = new Man();
        Woman woman = new Woman();

        Family family = new Family(man,woman);

        when(familyDao.getFamilyByIndex(anyInt())).thenReturn(family);

        Family family1 = familyService.getFamilyById(8);

        assertEquals(family,family1);

        verify(familyDao).getFamilyByIndex(8);
    }

    @Test
    public void testAddPet(){
        Pet pet = new Dog();
        Man man = new Man();
        Woman woman = new Woman();

        Family family = spy(new Family(man,woman));

        when(familyDao.getFamilyByIndex(anyInt())).thenReturn(family);
        doNothing().when(family).addPet(any(Pet.class));

        familyService.addPet(1,pet);

        verify(familyDao).getFamilyByIndex(1);
        verify(family).addPet(pet);
    }

    @Test
    public void testDeleteAllChildrenOlderThan(){
        Human child1 = spy(new Human());
        Human child2 = spy(new Human());
        Family family = mock(Family.class);

        when(child1.getAge()).thenReturn(10);
        when(child2.getAge()).thenReturn(20);

        when(family.getChildren()).thenReturn(List.of(child1,child2));
        when(familyDao.getAllFamilies()).thenReturn(List.of(family));

        familyService.deleteAllChildrenOlderThan(15);

        verify(family).deleteChild(child2);
        verify(family,never()).deleteChild(child1);

        verify(familyDao).saveFamily(family);
    }

}
