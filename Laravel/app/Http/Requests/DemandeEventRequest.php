<?php

namespace App\Http\Requests;

use Illuminate\Foundation\Http\FormRequest;

class DemandeEventRequest extends FormRequest
{
    /**
     * Determine if the user is authorized to make this request.
     *
     * @return bool
     */
    public function authorize()
    {
        return false;
    }

    /**
     * Get the validation rules that apply to the request.
     *
     * @return array
     */
    public function rules()
    {
        return [
            "responsableClubId"=> "required|unique:demande_event",
            "nomEvent"=> "required|min:2",
            "dateEvent"=> "required|date",
            "description"=> "required|min:2",
            
        ];
    }
}
