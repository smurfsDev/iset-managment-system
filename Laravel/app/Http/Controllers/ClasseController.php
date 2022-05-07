<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
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
}
