package com.projetIntegraion.spring.Etudiant.demandeCreationClub.service;

import java.util.List;

import com.projetIntegraion.spring.Etudiant.demandeCreationClub.entity.Member;
import com.projetIntegraion.spring.Etudiant.demandeCreationClub.repository.MemberRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
	@Autowired
	MemberRepository MemberRepository;

	public Member save(Member p) {
		return MemberRepository.save(p);
	}

	public Boolean existsById(Long id) {
		return MemberRepository.existsById(id);
	}

	public Member updateMember(Long id, Member p) {
		return null;
	}

	public void delete(Member p) {
		MemberRepository.delete(p);
	}

	public Boolean deleteById(Long id) {
		if (MemberRepository.existsById(id)) {
			MemberRepository.deleteById(id);
			return true;
		}
		return false;
	}

	public Member getMember(Long id) {
		return MemberRepository.existsById(id) == false ? null : MemberRepository.findById(id).get();
	}

	public List<Member> getAllMember() {
		return MemberRepository.findAll();
	}

	public Page<Member> getAllMemberParPage(int page, int size){
		return MemberRepository.findAll(PageRequest.of(page, size));
	};
}
