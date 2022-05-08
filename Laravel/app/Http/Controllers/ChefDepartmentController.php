<?php

namespace App\Http\Controllers;

use App\Models\User;
use Illuminate\Http\Request;

class ChefDepartmentController extends Controller
{
    public function show()
    {
        // users having roles relation containing 'chefDepartement'
        $users = User::whereHas('roles', function ($query) {
            $query->where('name', 'chefDepartement');
        })->with('roles')->paginate(5);

        return response()->json($users);
    }

    public function accept($id){
        $user = User::find($id);
        if($user){
            $user->roles()->updateExistingPivot(5, ['status' => 1]);
            $user->save();
            return response()->json('ChefDepartement accepted',200);
        }
        return response()->json('ChefDepartment not found',404);
    }

    public function refuse($id){
        $user = User::find($id);
        $user->roles()->updateExistingPivot(5, ['status' => 2]);
        $user->save();
        return response()->json('ChefDepartement refused',200);
    }
}
