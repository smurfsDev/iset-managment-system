package com.projetIntegraion.spring.blogClub.service;

import com.projetIntegraion.spring.blogClub.entity.Board;
import com.projetIntegraion.spring.blogClub.repository.BoardRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class BoardService {
    @Autowired
    private BoardRepository BoardRepository;

    public Board save(Board p) {
		return BoardRepository.save(p);
	}

	public Boolean existsById(Long id) {
		return BoardRepository.existsById(id);
	}

	public Board updateClub(Long id, Board p) {
		return null;
	}

	public void delete(Board p) {
		BoardRepository.delete(p);
	}

	public Boolean deleteById(Long id) {
		if (BoardRepository.existsById(id)) {
			BoardRepository.deleteById(id);
			return true;
		}
		return false;
	}

	public Board getClub(Long id) {
		return BoardRepository.existsById(id) == false ? null : BoardRepository.findById(id).get();
	}

	// public List<Club> getAllClubs() {
	// 	return boardRepository.findAll();
	// }
	public Board getBoard(Long id) {
		return BoardRepository.existsById(id) == false ? null : BoardRepository.findById(id).get();
	}
	public Page<Board> getAllBoardsParPage(int page, int size){
		return BoardRepository.findAll(PageRequest.of(page, size));
	};

	// public Page<Board> getClubParNom(String nom, int page, int size){
	// 	return boardRepository.findByNomBoardContains(nom, page, size); //(nom, PageRequest.of(page, size));
	// };
}
