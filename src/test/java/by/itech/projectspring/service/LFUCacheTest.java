//package by.itech.projectspring.service;
//
//
//import by.itech.projectspring.entity.User;
//import org.junit.Before;
//import org.junit.Test;
//
//
//import java.util.ArrayList;
//import java.util.UUID;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNull;
//
//
//public class LFUCacheTest {
//
//
//    LFUCache<User> lfuCacheTest = new LFUCache<>(0.7, 4);
//
//    User user1;
//    User user2;
//    User user3;
//    User user4;
//
//
//    @Before
//    public void init() {
//        lfuCacheTest.cleanCache();
//        user1 = new User(1, UUID.randomUUID(), "lastName1", "firstName1", new ArrayList<>());
//        user2 = new User(2, UUID.randomUUID(), "lastName2", "firstName2", new ArrayList<>());
//        user3 = new User(3, UUID.randomUUID(), "lastName3", "firstName3", new ArrayList<>());
//        user4 = new User(4, UUID.randomUUID(), "lastName4", "firstName4", new ArrayList<>());
//    }
//
//    @Test
//    public void addMethodTest() {
//       lfuCacheTest.add(user1, user1.getUuid());
//        assertEquals(lfuCacheTest.get(user1.getUuid()), user1);
//    }
//
//    @Test
//    public void removedLeastUsedTest() {
//        lfuCacheTest.add(user1, user1.getUuid());
//        lfuCacheTest.add(user2, user2.getUuid());
//        lfuCacheTest.add(user3, user3.getUuid());
//        lfuCacheTest.get(user2.getUuid());
//        lfuCacheTest.get(user2.getUuid());
//        lfuCacheTest.get(user3.getUuid());
//        lfuCacheTest.get(user3.getUuid());
//        lfuCacheTest.get(user3.getUuid());
//        lfuCacheTest.add(user4, user4.getUuid());
//        assertNull(lfuCacheTest.get(user1.getUuid()));
//    }
//
//    @Test
//    public void cleanCacheMethodTest() {
//        lfuCacheTest.add(user1, user1.getUuid());
//        lfuCacheTest.add(user2, user2.getUuid());
//        lfuCacheTest.cleanCache();
//        assertNull(lfuCacheTest.get(user1.getUuid()));
//        assertNull(lfuCacheTest.get(user2.getUuid()));
//    }
//}
