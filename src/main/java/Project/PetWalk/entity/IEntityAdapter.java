package Project.PetWalk.entity;

public interface IEntityAdapter<T> {
    void setCreateAt(T o);
    void setUpdateAt(T o);
}
