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
        })->get();

        return response()->json($users);
    }

    public function accept($id){
        $user = User::find($id);
        $user->roles()->updateExistingPivot(5, ['status' => 1]);
        $user->save();
        return response()->json('User accepted');


    }
}
