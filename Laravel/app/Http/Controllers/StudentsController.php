<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Models\User;
class StudentsController extends Controller
{
    public function show()
    {
        // users having roles relation containing 'chefDepartement'
        $users = User::whereHas('roles', function ($query) {
            $query->where('name', 'student');
        })->with('roles')->paginate(5);

        return response()->json($users);
    }

    public function accept($id){
        $user = User::find($id);
        if($user){
            $user->roles()->updateExistingPivot(2, ['status' => 1]);
            $user->save();
            return response()->json('student accepted',200);
        }
        return response()->json('student not found',404);
    }

    public function refuse($id){
        $user = User::find($id);
        $user->roles()->updateExistingPivot(2, ['status' => 2]);
        $user->save();
        return response()->json('student refused',200);
    }
}
