package market.service.impl;

import market.dao.RegionDAO;
import market.domain.Region;
import market.service.RegionService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Реализация сервиса региона.
 */
@Service
public class RegionServiceImpl implements RegionService {
	private final RegionDAO regionDAO;

	public RegionServiceImpl(RegionDAO regionDAO) {
		this.regionDAO = regionDAO;
	}

	@Transactional
	@Override
	public void save(Region region) {
		regionDAO.save(region);
	}

	@Transactional
	@Override
	public void delete(Region region) {
		regionDAO.delete(region);
	}

	@Transactional(readOnly = true)
	@Override
	public Region findOne(long regionId) {
		return regionDAO.findById(regionId).orElse(null);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Region> findAllOrderById() {
		return regionDAO.findAll(Sort.by(Sort.Direction.ASC, "id"));
	}

	@Transactional(readOnly = true)
	@Override
	public List<Region> findAllOrderByName() {
		return regionDAO.findAll(Sort.by(Sort.Direction.ASC, "name"));
	}
}
