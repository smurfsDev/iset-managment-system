<?php

namespace App\Http\Controllers;

use App\Models\User;
use Illuminate\Http\Request;

class ChefDepartmentController extends Controller
{
    /**
     * Display a listing of the chefDepartements.
     *
     * @return \Illuminate\Http\Response containing the list of resources
     */
    public function show()
    {
        // users having roles relation containing 'chefDepartement'
        $users = User::whereHas('roles', function ($query) {
            $query->where('name', 'chefDepartement');
        })->with('roles')->paginate(5);

        return response()->json($users);
    }

    /**
     * Accept a chefDepartment account request after checking if department have no chefDepartment.
     *
     * @param  \Illuminate\Http\Request  $request
     * @return \Illuminate\Http\Response
     */
    public function accept($id){
        $user = User::with('roles')->find($id);
        if($user){
            $chefDepartement = User::whereHas('roles', function ($query)use($user) {
                $query->where('name', 'chefDepartement');
                $query->where("status", "=", 1);
                $query->where('department', $user->roles[0]->pivot->department);
            })->first();
            if(!$chefDepartement){
                $user->roles()->updateExistingPivot(5, ['status' => 1]);
                $user->save();
                return response()->json('ChefDepartement accepted',200);
            }
            return response()->json(["msg"=>'La departmenet deja admet un chef department'],400);
        }
        return response()->json('ChefDepartment not found',404);
    }

    /**
     * Reject a chefDepartment account request .
     *
     * @param  \Illuminate\Http\Request  $request
     * @return \Illuminate\Http\Response
     */
    public function refuse($id){
        $user = User::find($id);
        $user->roles()->updateExistingPivot(5, ['status' => 2]);
        $user->save();
        return response()->json('ChefDepartement refused',200);
    }
}
