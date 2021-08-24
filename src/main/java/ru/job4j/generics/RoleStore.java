package ru.job4j.generics;

public class RoleStore extends UserStore<User> {

    private final Store<User> roleStore = new UserStore<>();

    @Override
    public void add(User model) {
        roleStore.add(model);
    }

    @Override
    public boolean replace(String id, User model) {
        return roleStore.replace(id, model);
    }

    @Override
    public boolean delete(String id) {
        return roleStore.delete(id);
    }

    @Override
    public User findById(String id) {
        return roleStore.findById(id);
    }
}
