<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Http\Requests\ClassesRequest;
use App\Models\Classe;

class ClasseController extends Controller
{
    public function show($id)
    {
        $classes = Classe::where('departement_id', $id)->get();
        if ($classes->isEmpty()) {
            return response()->json(['message' => 'Aucune classe n\'est disponible pour ce département'], 404);
        }
        return response()->json($classes, 200);
    }

    public function getClasses(Request $request)
    {
        // check for the user's role = chefDepartement
        $did = $request->user()->roles()->where('role_id', '5')->first();
        if ($did) {

            $did = $did->pivot->department;
            $classes = Classe::where('departement_id', $did)->paginate(5);

            if ($classes->isEmpty()) {
                return response()->json(['message' => 'Aucune classe n\'est disponible pour ce département'], 404);
            }
            return response()->json($classes, 200);
        }
        return response()->json(['message' => 'Vous n\'avez pas les droits pour accéder à cette ressource'], 403);
    }

    public function createClasse(ClassesRequest $request)
    {
        $did = $request->user()->roles()->where('role_id', '5')->first()->pivot->department;
        $classe = new Classe();
        $classe->departement_id = $did;
        $classe->nom = $request->nom;
        $classe->abreviation = $request->abreviation;

        if ($classe->save()) {
            return response()->json($classe, 200);
        }
        return response()->json(['message' => 'Erreur lors de la création de la classe'], 500);
    }

    public function updateClass(ClassesRequest $request, $id)
    {
        $classe = Classe::find($id);
        if ($classe) {
            $classe->nom = $request->nom;
            $classe->abreviation = $request->abreviation;

            if ($classe->save()) {
                return response()->json($classe, 200);
            }
            return response()->json(['message' => 'Erreur lors de la modification de la classe'], 500);
        }
        return response()->json(['message' => 'Classe introuvable'], 404);
    }

    public function deleteClass($id)
    {
        $classe = Classe::find($id);
        if ($classe) {
            if ($classe->delete()) {
                return response()->json(['message' => 'Classe supprimée'], 200);
            }
            return response()->json(['message' => 'Erreur lors de la suppression de la classe'], 500);
        }
        return response()->json(['message' => 'Classe introuvable'], 404);
    }

    public function getAll()
    {
        $classes = Classe::all();
        if (empty($classes)) {
            return response()->json(['message' => 'Aucune classe n\'est disponible pour ce département'], 404);
        }
        return response()->json($classes, 200);
    }
}
