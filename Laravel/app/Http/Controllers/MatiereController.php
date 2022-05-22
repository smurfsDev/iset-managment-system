<?php

namespace App\Http\Controllers;

use App\Models\Matiere;
use Illuminate\Http\Request;
use App\Http\Requests\MatiereRequest;
use App\Models\Classe;
use App\Models\Note;
use App\Models\User;

class MatiereController extends Controller
{
    public function getMatiereParClasse(Request $request,$id){


        $mat = Matiere::where('idClasse','=',$id)
        ->with('enseignant')
        ->paginate(5);
        return response()->json(["data" => $mat], 200);

    }

    public function createMatiere(MatiereRequest $request, $id){
        $matiere = new Matiere();
        $matiere->nom = $request->input('nom');
        $matiere->idClasse = $id;
        $matiere->idEnseignant = $request->input('idEnseignant');
        $matiere->semestre = $request->input('semestre');
        $matiere->coefficient = $request->input('coefficient');

        $matiere->save();

        $users = Classe::with('users')->find($id);
        $ids = $users->users->pluck('user_id')->toArray();
        foreach ($ids as $id){
            Note::create([
                'student_id' => $id,
                'matiere_id' => $matiere->id,
                'note' => 0,
            ]);
        }

        $users = Classe::find($id);



        return response()->json(["data" => $matiere], 201);
    }

    public function updateMatiere(MatiereRequest $request, $id){
        $matiere = Matiere::find($id);
        if ($matiere) {
            $matiere->nom = $request->input('nom') ? $request->input('nom') : $matiere->nom;
            $matiere->idEnseignant = $request->input('idEnseignant') ? $request->input('idEnseignant') : $matiere->idEnseignant;
            $matiere->semestre = $request->input('semestre') ? $request->input('semestre') : $matiere->semestre;
            $matiere->coefficient = $request->input('coefficient') ? $request->input('coefficient') : $matiere->coefficient;

            $matiere->save();
            return response()->json([
                'message' => 'Update Success',
                'id' => $matiere->id,
                'attributes' => $matiere
            ], 201);
        } else {
            return response()->json([
                "Matiere non trouvée"
            ], 404);
        }
    }

    public function deleteMatiere($id){
        $matiere = Matiere::find($id);
        if ($matiere) {
            $matiere->delete();
            return response()->json([], 204);
        } else {
            return response()->json([
                'type' => 'matiere',
                'message' => 'demande non trouvée'
            ], 404);
        }
    }

    public function getClassesEnseignéeParEnseignant(Request $request)
    {
        // $user = User::with("classe")->find(2);
        // $user = Classe::with("users.user")->find(1);
        // return $user;
        $idEns = $request->user()->id;
        $classes = Classe::whereHas('matieres', function ($query) use($idEns) {
            $query->where('idEnseignant', $idEns);
        })->with('matieres', function ($query) use($idEns) {
            $query->where('idEnseignant', $idEns);
        })
        ->with('matieres.notes.student')
        ->paginate(5);
        return response()->json($classes);    }

    public function getMyMatieres(Request $request)
    {
        $idStudent = $request->user()->id;
        $student = Note::where('student_id', $idStudent)->with('matiere')->paginate(5);

        return response()->json($student);
    }
}
