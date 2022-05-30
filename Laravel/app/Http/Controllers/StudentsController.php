<?php

namespace App\Http\Controllers;

use App\Models\Classe;
use App\Models\Note;
use Illuminate\Http\Request;
use App\Models\User;
use PhpParser\Builder\Class_;

class StudentsController extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function show()
    {
        // users having roles relation containing 'chefDepartement'
        $users = User::whereHas('roles', function ($query) {
            $query->where('name', 'student');
        })->with('roles')->paginate(5);

        return response()->json($users);
    }

    /**
     * accept a request to create an account for a student
     * @param Integer $id
     * @return \Illuminate\Http\Response
     */
    public function accept($id){
        $user = User::find($id);
        if($user){
            $user->roles()->updateExistingPivot(2, ['status' => 1]);
            $user->save();
            $classId = $user->roles()->where('role_id', 2)->first()->pivot->classe;
            $ListeMatiere = Classe::find($classId)->matieres;
            $idMatieres = $ListeMatiere->pluck('id')->toArray();

            foreach ($idMatieres as $idMatiere){
                if(empty(Note::where('student_id', $id)->where('matiere_id', $idMatiere)->first())){
                    Note::create([
                        'student_id' => $id,
                        'matiere_id' => $idMatiere,
                        'note' => 0,
                    ]);
                }
            }

            return response()->json('student accepted',200);
        }
        return response()->json('student not found',404);
    }

    /**
     * refuse a request to create an account for a student
     * @param Integer $id
     * @return \Illuminate\Http\Response
     */
    public function refuse($id){
        $user = User::find($id);
        $user->roles()->updateExistingPivot(2, ['status' => 2]);
        $user->save();
        return response()->json('student refused',200);
    }

    /**
     * Delete the specified resource.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function delete($id){
        $user = User::find($id);
        if($user){
            $user->roles()->detach(2);
            $user->delete();
            return response()->json('student deleted',200);
        }
        return response()->json('student not found',404);
    }
}
