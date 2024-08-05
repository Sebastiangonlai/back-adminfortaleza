package backend.academia.fortaleza.utils.converters;

public interface GenericConverter<E, D> {
    D convertToDto(E entity);
    E convertToEntity(D dto);
}
