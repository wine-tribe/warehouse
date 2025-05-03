package backend.service.impl;

import backend.dto.WarehouseGroupDto;
import backend.exception.ResourceNotFoundException;
import backend.mapper.WarehouseGroupMapper;
import backend.model.WarehouseGroup;
import backend.repository.WarehouseGroupRepository;
import backend.service.WarehouseGroupService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WarehouseGroupServiceImpl implements WarehouseGroupService {

    private final WarehouseGroupRepository warehouseGroupRepository;
    private final WarehouseGroupMapper warehouseGroupMapper;

    @Override
    @Transactional
    public WarehouseGroupDto getById(Long id) {
        WarehouseGroup warehouseGroup = warehouseGroupRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("WarehouseGroup not found with id: " + id));

        return warehouseGroupMapper.convertToDto(warehouseGroup);
    }

    @Override
    @Transactional
    public List<WarehouseGroupDto> getAll() {
        return warehouseGroupRepository.findAll().stream()
                .map(warehouseGroupMapper::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        WarehouseGroup warehouseGroup = warehouseGroupRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("WarehouseGroup not found with id: " + id));

        warehouseGroupRepository.delete(warehouseGroup);
    }

    @Override
    @Transactional
    public WarehouseGroupDto isDeleted(Long id, boolean isDeleted) {
        WarehouseGroup warehouseGroup = warehouseGroupRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("WarehouseGroup not found with id: " + id));

        warehouseGroup.setIsDeleted(isDeleted);
        warehouseGroup.setLastModifiedDate(Instant.now());

        return warehouseGroupMapper.convertToDto(warehouseGroupRepository.save(warehouseGroup));
    }

    @Override
    @Transactional
    public WarehouseGroup findById(Long warehouseGroupId) {
        return warehouseGroupRepository.findById(warehouseGroupId)
                .orElseThrow(() -> new ResourceNotFoundException("Warehouse not found with id: " + warehouseGroupId));
    }
}
